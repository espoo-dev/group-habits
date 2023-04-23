# == Schema Information
#
# Table name: item_service_orders
#
#  id               :bigint           not null, primary key
#  item_id          :integer          not null
#  service_order_id :integer          not null
#  created_at       :datetime         not null
#  updated_at       :datetime         not null
#
# Indexes
#
#  index_item_service_orders_on_item_id           (item_id)
#  index_item_service_orders_on_service_order_id  (service_order_id)
#
class ItemServiceOrder < ApplicationRecord
  belongs_to :item
  belongs_to :service_order
end
