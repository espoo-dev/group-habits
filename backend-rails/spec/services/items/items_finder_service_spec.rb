# frozen_string_literal: true

require 'rails_helper'

RSpec.describe Items::ItemsFinderService do
  describe '#call' do
    let!(:user) { create(:user) }
    let!(:item) { create(:item, name: 'abc', item_type: 'product', user:) }
    let!(:item2) { create(:item, name: 'def', item_type: 'service', user:) }
    let!(:item3) { create(:item) }

    context 'when not querying' do
      subject { described_class.new(user:, index_params: {}).call }
      it 'returns user categories' do
        is_expected.to eq [item, item2]
      end
    end

    context 'when querying' do
      context 'when by name like' do
        subject { described_class.new(user:, index_params: { name: 'b' }).call }
        it 'returns user categories matching filter' do
          is_expected.to eq [item]
        end
      end

      context 'when by item_type' do
        subject { described_class.new(user:, index_params: { item_type: 'service' }).call }
        it 'returns user categories matching filter' do
          is_expected.to eq [item2]
        end
      end
    end
  end
end
