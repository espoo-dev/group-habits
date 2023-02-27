# frozen_string_literal: true

require 'rails_helper'

RSpec.describe ServiceOrders::ServiceOrderCreatorService do
  describe '#call' do
    let!(:user) { create(:user) }
    let!(:customer) { create(:customer, user:) }
    let!(:item) { create(:item, user:) }
    let!(:params) do
      {
        name: 'service order name',
        status: 'budge',
        customer_id: customer.id,
        items_ids: [item.id]
      }
    end

    subject { described_class.new(user:, params:).call }

    it 'returns service order with proper fields', :aggregate_failures do
      subject
      expect(subject.name).to eq(params[:name])
      expect(subject.status).to eq(params[:status])
      expect(subject.customer_id).to eq(customer.id)
      expect(subject.user_id).to eq(user.id)
      expect(subject.items).to eq([item])
    end

    it 'creates service_order', :aggregate_failures do
      expect { subject }.to change { ServiceOrder.count }.from(0).to(1)
    end
  end
end
