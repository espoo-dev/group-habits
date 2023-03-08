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
require 'rails_helper'

RSpec.describe ItemServiceOrder, type: :model do
  context 'relationship' do
    it { should belong_to(:item).required }
    it { should belong_to(:service_order).required }
  end
end
