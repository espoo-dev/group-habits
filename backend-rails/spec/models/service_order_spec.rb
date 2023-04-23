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
require 'rails_helper'

RSpec.describe ServiceOrder, type: :model do
  context 'relationship' do
    it { should belong_to(:user).required }
    it { should belong_to(:customer).required }
    it { should have_many(:item_service_orders).dependent(:destroy) }
    it { should have_many(:items).through(:item_service_orders).dependent(:destroy) }
  end

  context 'inclusion' do
    it { should validate_inclusion_of(:status).in_array(ServiceOrder::STATUSES) }
  end

  describe '#products' do
    let(:service_order) { create(:service_order) }
    let(:products) { service_order.products }

    context 'when service order does not have a product' do
      it 'returns an empty array' do
        expect(products).to eq([])
      end
    end

    context 'when service order has a product' do
      let(:item) { create(:item) }
      let(:item_service) { create(:item, name: 'Setup softwares', extra_info: 'nice softwares', item_type: 'service') }

      before do
        create(:item_service_order, item_id: item.id, service_order_id: service_order.id)
        create(:item_service_order, item_id: item_service.id, service_order_id: service_order.id)
      end

      it 'returns products in service order' do
        expect(service_order.item_service_orders.count).to eq(2)
        expect(products.count).to eq(1)
        expect(products.first.name).to eq('laptop')
        expect(products.first.item_type).to eq('product')
      end
    end
  end

  describe '#services' do
    let(:service_order) { create(:service_order) }
    let(:services) { service_order.services }

    context 'when service order does not have a service' do
      it 'returns an empty array' do
        expect(services).to eq([])
      end
    end

    context 'when service order has a service' do
      let(:item) { create(:item) }
      let(:item_service) { create(:item, name: 'Setup softwares', extra_info: 'nice softwares', item_type: 'service') }
      let!(:item_service_order) { create(:item_service_order, item_id: item.id, service_order_id: service_order.id) }
      let!(:item_service_order2) do
        create(:item_service_order, item_id: item_service.id, service_order_id: service_order.id)
      end

      it 'returns services in service order' do
        expect(services.count).to eq(1)
        expect(services.first.name).to eq('Setup softwares')
        expect(services.first.item_type).to eq('service')
      end
    end
  end

  describe '#total_price_items' do
    let(:service_order) { create(:service_order) }
    let(:products) { service_order.products }
    let(:services) { service_order.services }
    let(:total_price_products) { described_class.total_price_items(products) }
    let(:total_price_services) { described_class.total_price_items(services) }

    context 'when service order does not have an item' do
      it 'returns sum of each type items' do
        expect(total_price_products).to eq(0)
        expect(total_price_services).to eq(0)
      end
    end

    context 'when service order has items' do
      let(:item) { create(:item) }
      let(:item_service) { create(:item, name: 'Setup softwares', extra_info: 'nice softwares', item_type: 'service') }
      let!(:item_service_order) { create(:item_service_order, item_id: item.id, service_order_id: service_order.id) }
      let!(:item_service_order2) do
        create(:item_service_order, item_id: item_service.id, service_order_id: service_order.id)
      end

      it 'returns sum of each type items' do
        expect(total_price_products).to eq(BigDecimal(2000))
        expect(total_price_services.to_s).to eq('2000.0')
      end
    end
  end
end
