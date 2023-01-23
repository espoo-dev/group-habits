# frozen_string_literal: true

require 'rails_helper'

RSpec.describe ItemsFinderService do
  describe '#call' do
    let!(:user) { create(:user) }
    let!(:item) { create(:item, name: 'abc', user:) }
    let!(:item2) { create(:item, name: 'def', user:) }
    let!(:item3) { create(:item) }

    context 'when not querying' do
      subject { described_class.new(user:, find_params: {}).call }
      it 'returns user categories' do
        is_expected.to eq [item, item2]
      end
    end

    context 'when querying' do
      subject { described_class.new(user:, find_params: { name: 'b' }).call }
      it 'returns user categories matching filter' do
        is_expected.to eq [item]
      end
    end
  end
end
