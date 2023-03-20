# frozen_string_literal: true

require 'rails_helper'

RSpec.describe ServiceOrders::ServiceOrderFindService do
  describe '#call' do
    let!(:user) { create(:user) }

    context 'when service_order exists' do
      let!(:service_order) { create(:service_order, user:) }
      subject { described_class.new(user:, params: { id: service_order.id }).call }
      it 'returns service order' do
        is_expected.to eq service_order
      end
    end

    context 'when service_order does not exist' do
      subject { described_class.new(user:, params: { id: -1 }).call }
      it 'returns nil' do
        is_expected.to be_nil
      end
    end
  end
end
