# frozen_string_literal: true

require 'rails_helper'

RSpec.describe CustomerPresenter do
  let(:customer) { build(:customer) }

  describe '#payload' do
    subject { described_class.new(customer).payload }

    it 'matches expected attributes' do
      expected_payload = {
        id: customer.id,
        name: customer.name,
        document_number: customer.document_number,
        phone: customer.phone,
        state_inscription: customer.state_inscription,
        customer_type: customer.customer_type
      }

      is_expected.to eq expected_payload
    end
  end
end
