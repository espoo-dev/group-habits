# frozen_string_literal: true

require 'rails_helper'

RSpec.describe CategoryUpdaterService do
  describe '#call' do
    let!(:user) { create(:user) }
    let!(:new_category_name) { create(:user) }
    let(:update_category_params) { { id: category.id, name: new_category_name  } }

    subject { described_class.new(user:, update_category_params:).call }

    describe 'when user is category owner' do
      let!(:category) { create(:category, user:) }

      it 'updates category name' do
        expect { subject }.to change { category.reload.name }
      end
    end

    describe 'when user is not category owner' do
      let!(:category) { create(:category) }

      it 'raises not authorized error' do
        expect { subject }.to raise_error Pundit::NotAuthorizedError
      end
    end
  end
end
