# frozen_string_literal: true

require 'rails_helper'

RSpec.describe CategoryPresenter do
  describe '#payload' do
    let!(:category) { create(:category) }
    subject { described_class.new(category).payload }

    it 'matches expected attributes' do
      expected_payload = {
        id: category.id,
        name: category.name
      }

      is_expected.to eq expected_payload
    end
  end
end
