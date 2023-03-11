# frozen_string_literal: true

require 'rails_helper'

RSpec.describe ServiceOrderPresenter do
  describe '#payload' do
    subject { described_class.new(service_order).payload }
    let(:service_order) { build(:service_order) }

    it 'matches expected attributes' do
      expected_payload = {
        id: service_order.id,
        extra_info: service_order.extra_info,
        status: service_order.status,
        creation_date: DateUtil.format_date(service_order.creation_date),
        conclusion_date: DateUtil.format_date(service_order.conclusion_date),
        discount: service_order.discount.to_f,
        customer: CustomerPresenter.payload_for_item(service_order.customer),
        items: ItemPresenter.payload_for_list(service_order.items)
      }

      is_expected.to eq expected_payload
    end
  end
end
