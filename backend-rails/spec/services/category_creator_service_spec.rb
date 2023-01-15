# frozen_string_literal: true

require 'rails_helper'

RSpec.describe CategoryCreatorService do
  describe '#call' do
    let!(:user) { create(:user) }
    let!(:create_category_params) { attributes_for(:category, user:) }

    subject { described_class.new(user:, create_category_params:).call }

    it 'creates category' do
      expect { subject }.to change { Category.count }.from(0).to(1)
    end
  end
end
