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

  def products
    products = []

    item_service_orders.each do |item_service_order|
      products << item_service_order if item_service_order.item.item_type == 'product'
    end
    products
  end

  def services
    services = []

    item_service_orders.each do |item_service_order|
      services << item_service_order if item_service_order.item.item_type == 'service'
    end
    services
  end

  def total_price_items(items)
    total = 0

    items.each do |item_service_order|
      total += item_service_order.item.sale_price
    end
    total
  end
end
