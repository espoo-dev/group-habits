# frozen_string_literal: true

require 'rails_helper'

RSpec.describe Items::ItemCreatorService do
  describe '#call' do
    let!(:user) { create(:user) }
    let!(:category) { create(:category, user:) }
    let!(:sales_unit) { create(:sales_unit) }
    let!(:create_item_params) do
      {
        name: 'phone',
        extra_info: 'nice phone',
        sale_price: 1000.50,
        purchase_price: 500.25,
        item_type: 'product',
        category_id: category.id,
        sales_unit_id: sales_unit.id
      }
    end

    subject { described_class.new(user:, create_item_params:).call }

    it 'returns customer with proper fields', :aggregate_failures do
      subject
      expect(subject.name).to eq(create_item_params[:name])
      expect(subject.extra_info).to eq(create_item_params[:extra_info])
      expect(subject.sale_price).to eq(create_item_params[:sale_price])
      expect(subject.purchase_price).to eq(create_item_params[:purchase_price])
      expect(subject.item_type).to eq(create_item_params[:item_type])
      expect(subject.category_id).to eq(create_item_params[:category_id])
      expect(subject.sales_unit_id).to eq(create_item_params[:sales_unit_id])
    end

    it 'creates item', :aggregate_failures do
      expect { subject }.to change { Item.count }.from(0).to(1)
    end
  end
end
