# frozen_string_literal: true

require 'rails_helper'

RSpec.describe CategoryUpdaterService do
  describe '#call' do
    let!(:user) { create(:user) }
    let!(:update_category_params) { { id: category.id } }

    subject { described_class.new(user:, update_category_params:).call }

    describe 'when user is category owner' do
      let!(:category) { update(:category) }
      it 'updates category' do
        expect { subject }.to change { Category.name }
      end
    end

    describe 'when user is not category owner' do
      let!(:category) { update(:category) }
      it 'updates category' do
        expect { subject }.to raise_error Pundit::NotAuthorizedError
      end
    end
  end
end
