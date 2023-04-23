# == Schema Information
#
# Table name: service_orders
#
#  id              :bigint           not null, primary key
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
#  index_service_orders_on_customer_id  (customer_id)
#  index_service_orders_on_user_id      (user_id)
#
class ServiceOrder < ApplicationRecord
  include NameFilterable

  STATUSES = %w[budge waiting_resources approved in_progress canceled finished invoiced].freeze

  belongs_to :user
  belongs_to :customer
  has_many :item_service_orders, dependent: :destroy
  has_many :items, through: :item_service_orders, dependent: :destroy

  validates :status, inclusion: STATUSES

  def self.total_price_items(items)
    items.sum(&:sale_price)
  end

  def products
    items.select { |item| item.item_type == 'product' }
  end

  def services
    items.select { |item| item.item_type == 'service' }
  end
end
