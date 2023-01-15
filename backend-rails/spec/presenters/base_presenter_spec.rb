# frozen_string_literal: true

require 'rails_helper'

RSpec.describe BasePresenter do
  let(:category) { build(:category) }

  describe '.payload_for_item' do
    subject { CategoryPresenter.payload_for_item(category) }

    it 'formats single object' do
      expected_payload = {
        id: category.id,
        name: category.name
      }

      is_expected.to eq expected_payload
    end
  end

  describe '.payload_for_list' do
    subject { CategoryPresenter.payload_for_list([category]) }

    it 'formats single object' do
      expected_payload = [{
        id: category.id,
        name: category.name
      }]

      is_expected.to eq expected_payload
    end
  end
end
