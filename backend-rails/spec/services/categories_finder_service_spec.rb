# frozen_string_literal: true

require 'rails_helper'

RSpec.describe CategoriesFinderService do
  describe '#call' do
    let!(:user) { create(:user) }
    let!(:category) { create(:category, name: 'abc', user:) }
    let!(:category2) { create(:category, name: 'def', user:) }
    let!(:category3) { create(:category) }

    context 'when not querying' do
      subject { described_class.new(user:, find_params: {}).call }
      it 'returns user categories' do
        is_expected.to eq [category, category2]
      end
    end

    context 'when querying' do
      subject { described_class.new(user:, find_params: { name: 'b' }).call }
      it 'returns user categories matching filter' do
        is_expected.to eq [category]
      end
    end
  end
end
