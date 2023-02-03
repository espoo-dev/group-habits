# == Schema Information
#
# Table name: items
#
#  id             :bigint           not null, primary key
#  name           :string           not null
#  extra_info     :string
#  sale_price     :decimal(8, 2)    not null
#  purchase_price :decimal(8, 2)
#  sales_unit     :string           not null
#  item_type      :string           not null
#  category_id    :string           not null
#  user_id        :string           not null
#  created_at     :datetime         not null
#  updated_at     :datetime         not null
#
# Indexes
#
#  index_items_on_category_id       (category_id)
#  index_items_on_user_id           (user_id)
#  index_items_on_user_id_and_name  (user_id,name) UNIQUE
#
require 'rails_helper'

RSpec.describe Item, type: :model do
  context 'relationship' do
    it { should belong_to(:user).required }
    it { should belong_to(:category).optional }
  end

  context 'validations' do
    context 'presence' do
      it { should validate_presence_of(:name) }
      it { should validate_presence_of(:sale_price) }
      it { should validate_presence_of(:sales_unit) }
      it { should validate_presence_of(:item_type) }

      context 'category' do
        before { subject.valid? }
        subject { build(:item, item_type:, category:) }

        context 'when item_type is product' do
          let(:item_type) { 'product' }

          context 'when has category' do
            let(:category) { build(:category) }

            it 'must be valid' do
              expect(subject).to be_valid
            end
          end

          context 'when has no category' do
            let(:category) { nil }

            it 'must be invalid' do
              expect(subject).to_not be_valid
            end
          end
        end

        context 'when item_type is service' do
          let(:item_type) { 'service' }

          context 'when has category' do
            let(:category) { build(:category) }

            it 'must be valid' do
              expect(subject).to be_valid
            end
          end

          context 'when has no category' do
            let(:category) { nil }

            it 'must be valid' do
              expect(subject).to be_valid
            end
          end
        end
      end
    end

    context 'inclusion' do
      it { should validate_inclusion_of(:item_type).in_array(Item::ITEM_TYPES) }
    end

    context 'uniqueness' do
      subject { create(:item) }
      it { should validate_uniqueness_of(:name).scoped_to(:user_id).case_insensitive }
    end
  end

  context 'scopes' do
    context '.by_name_like' do
      let!(:item1) { create(:item, name: 'abcde') }
      let!(:item2) { create(:item, name: 'bcd') }
      let!(:item3) { create(:item, name: 'abc') }
      let!(:item4) { create(:item, name: 'bcde') }
      context 'when name_like is present' do
        subject { described_class.by_name_like('bcd') }

        it 'returns categories with name like' do
          is_expected.to match_array([item1, item2, item4])
        end
      end
      context 'when name_like is not present' do
        subject { described_class.by_name_like(nil) }

        it 'returns all categories' do
          is_expected.to match_array([item1, item2, item3, item4])
        end
      end
    end

    context '.by_item_type' do
      let!(:item1) { create(:item, item_type: 'product') }
      let!(:item2) { create(:item, item_type: 'product') }
      let!(:item3) { create(:item, item_type: 'service') }
      let!(:item4) { create(:item, item_type: 'service') }
      subject { described_class.by_item_type(item_type) }

      context 'when item_type is product' do
        let(:item_type) { 'product' }

        it 'returns items with item_type product' do
          is_expected.to match_array([item1, item2])
        end
      end
      context 'when item_type is service' do
        let(:item_type) { 'service' }

        it 'returns items with item_type service' do
          is_expected.to match_array([item3, item4])
        end
      end
      context 'when item_type is nil' do
        let(:item_type) { nil }

        it 'returns all items' do
          is_expected.to match_array([item1, item2, item3, item4])
        end
      end
    end
  end
end
