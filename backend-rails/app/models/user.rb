# == Schema Information
#
# Table name: users
#
#  id                     :integer          not null, primary key
#  email                  :string
#  encrypted_password     :string           default(""), not null
#  reset_password_token   :string
#  reset_password_sent_at :datetime
#  allow_password_change  :boolean          default(FALSE)
#  sign_in_count          :integer          default(0), not null
#  current_sign_in_at     :datetime
#  last_sign_in_at        :datetime
#  current_sign_in_ip     :inet
#  last_sign_in_ip        :inet
#  first_name             :string           default("")
#  last_name              :string           default("")
#  username               :string           default("")
#  created_at             :datetime         not null
#  updated_at             :datetime         not null
#  provider               :string           default("email"), not null
#  uid                    :string           default(""), not null
#  tokens                 :json
#  group_id               :integer
#
# Indexes
#
#  index_users_on_email                 (email) UNIQUE
#  index_users_on_group_id              (group_id)
#  index_users_on_reset_password_token  (reset_password_token) UNIQUE
#  index_users_on_uid_and_provider      (uid,provider) UNIQUE
#

class User < ApplicationRecord
  # Include default devise modules. Others available are:
  # :confirmable, :lockable, :timeoutable and :omniauthable
  devise :database_authenticatable, :registerable,
         :recoverable, :trackable, :validatable
  include DeviseTokenAuth::Concerns::User

  has_many :daily_habits, dependent: :destroy
  has_many :categories, dependent: :destroy
  has_many :items, dependent: :destroy
  belongs_to :group, optional: true
  validates :uid, uniqueness: { scope: :provider }

  before_validation :init_uid

  def full_name
    return username if first_name.blank?

    "#{first_name} #{last_name}"
  end

  def self.from_social_provider(provider, user_params)
    where(provider:, uid: user_params['id']).first_or_create! do |user|
      user.password = Devise.friendly_token[0, 20]
      user.assign_attributes user_params.except('id')
    end
  end

  # :reek:DuplicateMethodCall :reek:FeatureEnvy
  def generate_bearer_token(token)
    tokens[token.client] = {
      token: token.token_hash,
      expiry: token.expiry
    }
    bearer_token(token.token, token.client)
  end

  private

  def init_uid
    self.uid = email if uid.blank? && provider == 'email'
  end

  # https://github.com/lynndylanhurley/devise_token_auth/blob/30b6d30037ea646646e58ba2ec5ff4682b654f2c/app/models/devise_token_auth/concerns/user.rb
  # :reek:DuplicateMethodCall :reek:FeatureEnvy
  def bearer_token(token, client)
    # client may use expiry to prevent validation request if expired
    # must be cast as string or headers will break
    expiry = tokens[client]['expiry'] || tokens[client][:expiry]
    headers_names = DeviseTokenAuth.headers_names
    headers = {
      headers_names[:'access-token'] => token,
      headers_names[:'token-type'] => 'Bearer',
      headers_names[:client] => client,
      headers_names[:expiry] => expiry.to_s,
      headers_names[:uid] => uid
    }
    "Bearer #{Base64.strict_encode64(headers.to_json)}"
  end
end
