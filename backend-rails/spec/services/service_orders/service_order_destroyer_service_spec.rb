# frozen_string_literal: true

require 'rails_helper'

RSpec.describe ServiceOrders::ServiceOrderDestroyerService do
  describe '#call' do
    let!(:user) { create(:user) }
    let!(:params) { { id: service_order.id } }

    subject { described_class.new(user:, params:).call }

    describe 'when user is service_order owner' do
      let!(:service_order) { create(:service_order, user:) }
      it 'destroys service_order' do
        expect { subject }.to change { ServiceOrder.count }.from(1).to(0)
      end
    end

    describe 'when user is not service_order owner' do
      let!(:service_order) { create(:service_order) }
      it 'raises a NotAuthorizedError error' do
        expect { subject }.to raise_error Pundit::NotAuthorizedError
      end
    end
  end
end
