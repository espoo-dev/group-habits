# frozen_string_literal: true

require 'rails_helper'

RSpec.describe ServiceOrders::ServiceOrdersFinderService do
  describe '#call' do
    let!(:user) { create(:user) }
    let!(:service_order) { create(:service_order, name: 'abc', user:) }
    let!(:service_order2) { create(:service_order, name: 'def', user:) }
    let!(:service_order3) { create(:service_order) }

    context 'when not querying' do
      subject { described_class.new(user:, params: {}).call }
      it 'returns user categories' do
        is_expected.to eq [service_order, service_order2]
      end
    end

    context 'when querying' do
      context 'when by name like' do
        subject { described_class.new(user:, params: { name: 'b' }).call }
        it 'returns user categories matching filter' do
          is_expected.to eq [service_order]
        end
      end
    end
  end
end
