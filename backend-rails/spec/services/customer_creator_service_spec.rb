# frozen_string_literal: true

require 'rails_helper'

RSpec.describe CustomerCreatorService do
  describe '#call' do
    let!(:user) { create(:user) }
    let!(:create_customer_params) do
      {
        name: 'john',
        document_number: '0032133335',
        customer_type: 'business',
        phone: '85996857985',
        state_inscription: 'something'
      }
    end

    subject { described_class.new(user:, create_customer_params:).call }

    it 'returns customer with proper fields', :aggregate_failures do
      subject
      expect(subject.name).to eq(create_customer_params[:name])
      expect(subject.name).to eq(create_customer_params[:name])
      expect(subject.document_number).to eq(create_customer_params[:document_number])
      expect(subject.customer_type).to eq(create_customer_params[:customer_type])
      expect(subject.phone).to eq(create_customer_params[:phone])
      expect(subject.state_inscription).to eq(create_customer_params[:state_inscription])
    end

    it 'creates customer', :aggregate_failures do
      expect { subject }.to change { Customer.count }.from(0).to(1)
    end
  end
end
