# frozen_string_literal: true

require 'rails_helper'

RSpec.describe SalesUnitPresenter do
  let(:sales_unit) { build(:sales_unit) }

  describe '#payload' do
    subject { described_class.new(sales_unit).payload }

    it 'matches expected attributes' do
      expected_payload = {
        id: sales_unit.id,
        name: sales_unit.name
      }

      is_expected.to eq expected_payload
    end
  end
end
