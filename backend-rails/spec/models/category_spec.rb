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
