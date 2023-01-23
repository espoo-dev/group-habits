# == Schema Information
#
# Table name: categories
#
#  id         :bigint           not null, primary key
#  name       :string           not null
#  user_id    :integer          not null
#  created_at :datetime         not null
#  updated_at :datetime         not null
#
# Indexes
#
#  index_categories_on_user_id           (user_id)
#  index_categories_on_user_id_and_name  (user_id,name) UNIQUE
#
class Category < ApplicationRecord
  belongs_to :user
  has_many :items, dependent: :destroy

  validates :name, presence: true
  validates :name, uniqueness: { scope: :user_id, case_sensitive: false }

  scope :by_name_like, lambda { |name_like|
    where('name LIKE ?', "%#{name_like}%").order(:id)
  }
end
