# frozen_string_literal: true

require 'rails_helper'

RSpec.describe Customers::CustomerDestroyerService do
  describe '#call' do
    let!(:user) { create(:user) }
    let!(:destroy_params) { { id: customer.id } }

    subject { described_class.new(user:, destroy_params:).call }

    describe 'when user is customer owner' do
      let!(:customer) { create(:customer, user:) }
      it 'destroys customer' do
        expect { subject }.to change { Customer.count }.from(1).to(0)
      end
    end

    describe 'when user is not customer owner' do
      let!(:customer) { create(:customer) }
      it 'destroys customer' do
        expect { subject }.to raise_error Pundit::NotAuthorizedError
      end
    end
  end
end
