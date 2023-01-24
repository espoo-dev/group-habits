# == Schema Information
#
# Table name: customers
#
#  id                :bigint           not null, primary key
#  name              :string           not null
#  document_number   :string           not null
#  phone             :string           not null
#  state_inscription :string
#  customer_type     :integer          not null
#  user_id           :string           not null
#  created_at        :datetime         not null
#  updated_at        :datetime         not null
#
# Indexes
#
#  index_customers_on_user_id                      (user_id)
#  index_customers_on_user_id_and_document_number  (user_id,document_number) UNIQUE
#  index_customers_on_user_id_and_name             (user_id,name) UNIQUE
#
class Customer < ApplicationRecord

  enum :customer_type, [ :person, :business ]

  belongs_to :user
  validates :name, presence: true
  validates :document_number, presence: true
  validates :phone, presence: true

  validates :name, uniqueness: { scope: :user_id, case_sensitive: false }
  validates :document_number, uniqueness: { scope: :user_id, case_sensitive: false }

end
