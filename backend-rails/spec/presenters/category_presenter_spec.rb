# frozen_string_literal: true

require 'rails_helper'

RSpec.describe CategoryPresenter do
  let(:category) { build(:category) }

  describe '#payload' do
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
