# frozen_string_literal: true

require 'rails_helper'

RSpec.describe CustomersFinderService do
  describe '#call' do
    let!(:user) { create(:user) }
    let!(:customer) { create(:customer, name: 'abc', customer_type: Customer.customer_types[:person], user:) }
    let!(:customer2) { create(:customer, name: 'def', customer_type: Customer.customer_types[:business], user:) }
    let!(:customer3) { create(:customer) }

    context 'when not querying' do
      subject { described_class.new(user:, index_params: {}).call }
      it 'returns user customers' do
        is_expected.to eq [customer, customer2]
      end
    end

    context 'when querying' do
      context 'when by name like' do
        subject { described_class.new(user:, index_params: { name: 'b' }).call }
        it 'returns user customers matching filter' do
          is_expected.to eq [customer]
        end
      end

      context 'when by customer_type' do
        subject { described_class.new(user:, index_params: { customer_type: 'business' }).call }
        it 'returns user customers matching filter' do
          is_expected.to eq [customer2]
        end
      end
    end
  end
end
