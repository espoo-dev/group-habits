# frozen_string_literal: true

require 'rails_helper'

RSpec.describe ServiceOrders::ServiceOrderUpdaterService do
  describe '#call' do
    let!(:user) { create(:user) }
    let!(:service_order) { create(:service_order, name: 'abc', user:, items: create_list(:item, 2)) }
    let!(:items_ids) { create_list(:item, 3).pluck(:id) }
    let!(:name) { 'new name' }
    let!(:extra_info) { 'new extra_info' }
    let!(:status) { 'waiting_resources' }
    let!(:creation_date) { DateTime.new(2022, 1, 25) }
    let!(:conclusion_date) { DateTime.new(2022, 1, 26) }
    let!(:discount) { 1234 }
    let!(:customer_id) { create(:customer).id }

    let(:params) do
      {
        id: service_order.id,
        name:,
        extra_info:,
        status:,
        creation_date:,
        conclusion_date:,
        discount:,
        customer_id:,
        items_ids:
      }
    end

    subject { described_class.new(user:, params:).call }

    it 'updates service_order data ' do
      expect(subject.name).to eq(name)
      expect(subject.extra_info).to eq(extra_info)
      expect(subject.status).to eq(status)
      expect(subject.creation_date).to eq(creation_date)
      expect(subject.conclusion_date).to eq(conclusion_date)
      expect(subject.discount).to eq(discount)
      expect(subject.customer_id).to eq(customer_id)
      expect(subject.items.pluck(:id)).to eq(items_ids)
    end
  end
end
