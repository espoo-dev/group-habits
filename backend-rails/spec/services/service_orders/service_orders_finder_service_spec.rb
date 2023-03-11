# frozen_string_literal: true

require 'rails_helper'

RSpec.describe ServiceOrders::ServiceOrdersFinderService do
  describe '#call' do
    let!(:user) { create(:user) }
    let!(:service_order) { create(:service_order, user:) }
    let!(:service_order2) { create(:service_order, user:) }
    let!(:service_order3) { create(:service_order) }

    context 'when not querying' do
      subject { described_class.new(user:, params: {}).call }
      it 'returns user categories' do
        is_expected.to eq [service_order, service_order2]
      end
    end
  end
end
