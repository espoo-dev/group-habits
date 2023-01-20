# == Schema Information
#
# Table name: categories
#
#  id         :bigint           not null, primary key
#  name       :string           not null
#  user_id    :integer          not null
#  created_at :datetime         not null
#  updated_at :datetime         not null
#
# Indexes
#
#  index_categories_on_user_id           (user_id)
#  index_categories_on_user_id_and_name  (user_id,name) UNIQUE
#
require 'rails_helper'

RSpec.describe Category, type: :model do
  context 'relationship' do
    it { should belong_to(:user).required }
  end

  context 'validations' do
    it { should validate_presence_of(:name) }

    context 'uniqueness' do
      subject { create(:category) }
      it { should validate_uniqueness_of(:name).scoped_to(:user_id).case_insensitive }
    end
  end

  context 'scopes' do
    context '.by_name_like' do
      let!(:category1) { create(:category, name: 'abcde') }
      let!(:category2) { create(:category, name: 'bcd') }
      let!(:category3) { create(:category, name: 'abc') }
      let!(:category4) { create(:category, name: 'bcde') }
      context 'when name_like is present' do
        subject { described_class.by_name_like('bcd') }

        it 'returns categories with name like' do
          is_expected.to match_array([category1, category2, category4])
        end
      end
      context 'when name_like is not present' do
        subject { described_class.by_name_like(nil) }

        it 'returns all categories' do
          is_expected.to match_array([category1, category2, category3, category4])
        end
      end
    end
  end
end
