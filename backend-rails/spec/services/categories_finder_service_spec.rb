# frozen_string_literal: true

require 'rails_helper'

RSpec.describe CategoriesFinderService do
  describe '#call' do
    let!(:user) { create(:user) }
    let!(:category) { create(:category, user:) }
    let!(:category2) { create(:category) }

    subject { described_class.new(user:).call }

    it 'returns user categories expected attributes' do
      is_expected.to eq [category]
    end
  end
end
