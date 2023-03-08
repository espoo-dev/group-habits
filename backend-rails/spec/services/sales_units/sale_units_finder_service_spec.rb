# frozen_string_literal: true

require 'rails_helper'

RSpec.describe SalesUnits::SalesUnitsFinderService do
  describe '#call' do
    let!(:sales_units) { create_list(:sales_unit, 2) }

    subject { described_class.new(user: nil, params: nil).call }
    it 'returns all sales units' do
      is_expected.to eq sales_units
    end
  end
end
