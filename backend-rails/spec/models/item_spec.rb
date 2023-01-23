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
    it { should belong_to(:category).required }
  end

  context 'validations' do
    context 'presence' do
      it { should validate_presence_of(:name) }
      it { should validate_presence_of(:sale_price) }
      it { should validate_presence_of(:sales_unit) }
      it { should validate_presence_of(:item_type) }
    end

    context 'inclusion' do
      it { should validate_inclusion_of(:item_type).in_array(Item::ITEM_TYPES) }
    end

    context 'uniqueness' do
      subject { create(:category) }
      it { should validate_uniqueness_of(:name).scoped_to(:user_id).case_insensitive }
    end
  end
end
