# frozen_string_literal: true

require 'rails_helper'

RSpec.describe Items::ItemDestroyerService do
  describe '#call' do
    let!(:user) { create(:user) }
    let!(:params) { { id: item.id } }

    subject { described_class.new(user:, params:).call }

    describe 'when user is item owner' do
      let!(:item) { create(:item, user:) }
      it 'destroys item' do
        expect { subject }.to change { Item.count }.from(1).to(0)
      end
    end

    describe 'when user is not item owner' do
      let!(:item) { create(:item) }
      it 'raises a NotAuthorizedError error' do
        expect { subject }.to raise_error Pundit::NotAuthorizedError
      end
    end
  end
end
