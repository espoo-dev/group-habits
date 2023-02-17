# frozen_string_literal: true

require 'rails_helper'

RSpec.describe Customers::CustomerCreatorService do
  describe '#call' do
    let!(:user) { create(:user) }
    let!(:params) do
      {
        name: 'john',
        document_number: '0032133335',
        customer_type: 'business',
        phone: '85996857985',
        state_inscription: 'something'
      }
    end

    subject { described_class.new(user:, params:).call }

    it 'returns customer with proper fields', :aggregate_failures do
      subject
      expect(subject.name).to eq(params[:name])
      expect(subject.name).to eq(params[:name])
      expect(subject.document_number).to eq(params[:document_number])
      expect(subject.customer_type).to eq(params[:customer_type])
      expect(subject.phone).to eq(params[:phone])
      expect(subject.state_inscription).to eq(params[:state_inscription])
    end

    it 'creates customer', :aggregate_failures do
      expect { subject }.to change { Customer.count }.from(0).to(1)
    end
  end
end
