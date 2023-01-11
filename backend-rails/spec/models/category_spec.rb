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
  describe 'relationship' do
    it { should belong_to(:user).required }
  end

  describe 'validations' do
    it { should validate_presence_of(:name) }

    describe 'uniqueness' do
      subject { create(:category) }
      it { should validate_uniqueness_of(:name).scoped_to(:user_id).case_insensitive }
    end
  end
end
