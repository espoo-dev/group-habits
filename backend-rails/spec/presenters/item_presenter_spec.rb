# frozen_string_literal: true

require 'rails_helper'

RSpec.describe ItemPresenter do
  let(:item) { build(:item) }

  describe '#payload' do
    subject { described_class.new(item).payload }

    it 'matches expected attributes' do
      expected_payload = {
        id: item.id,
        name: item.name,
        extra_info: item.extra_info,
        sale_price: item.sale_price,
        purchase_price: item.purchase_price,
        item_type: item.item_type,
        category: CategoryPresenter.payload_for_item(item.category),
        sales_unit: SalesUnitPresenter.payload_for_item(item.sales_unit)
      }

      is_expected.to eq expected_payload
    end
  end
end
