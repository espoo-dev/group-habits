# == Schema Information
#
# Table name: service_orders
#
#  id              :bigint           not null, primary key
#  name            :string           not null
#  extra_info      :string
#  status          :string           not null
#  creation_date   :datetime
#  conclusion_date :datetime
#  discount        :decimal(8, 2)
#  user_id         :integer          not null
#  customer_id     :integer          not null
#  created_at      :datetime         not null
#  updated_at      :datetime         not null
#
# Indexes
#
#  index_service_orders_on_customer_id       (customer_id)
#  index_service_orders_on_user_id           (user_id)
#  index_service_orders_on_user_id_and_name  (user_id,name) UNIQUE
#
class ServiceOrder < ApplicationRecord
  STATUSES = %w[budge waiting_resources approved in_progress canceled finished invoiced].freeze

  belongs_to :user
  belongs_to :customer

  validates :name, presence: true
  validates :name, uniqueness: { scope: :user_id, case_sensitive: false }
  validates :status, inclusion: STATUSES
end
