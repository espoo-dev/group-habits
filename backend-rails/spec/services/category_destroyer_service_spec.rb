# frozen_string_literal: true

require 'rails_helper'

RSpec.describe CategoryDestroyerService do
  describe '#call' do
    let!(:user) { create(:user) }
    let!(:destroy_params) { { id: category.id } }

    subject { described_class.new(user:, destroy_params:).call }

    describe 'when user is category owner' do
      let!(:category) { create(:category, user:) }
      it 'destroys category' do
        expect { subject }.to change { Category.count }.from(1).to(0)
      end
    end

    describe 'when user is not category owner' do
      let!(:category) { create(:category) }
      it 'destroys category' do
        expect { subject }.to raise_error Pundit::NotAuthorizedError
      end
    end
  end
end
