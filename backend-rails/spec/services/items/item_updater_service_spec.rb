# frozen_string_literal: true

require 'rails_helper'

RSpec.describe Items::ItemUpdaterService do
  describe '#call' do
    let!(:user) { create(:user) }
    let!(:new_item_name) { create(:user) }
    let(:update_item_params) { { id: item.id, name: new_item_name } }

    subject { described_class.new(user:, update_item_params:).call }

    describe 'when user is item owner' do
      let!(:item) { create(:item, user:) }

      it 'updates item name' do
        expect { subject }.to change { item.reload.name }
      end
    end

    describe 'when user is not item owner' do
      let!(:item) { create(:item) }

      it 'raises not authorized error' do
        expect { subject }.to raise_error Pundit::NotAuthorizedError
      end
    end
  end
end
