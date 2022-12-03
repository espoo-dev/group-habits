# == Schema Information
#
# Table name: groups
#
#  id         :bigint           not null, primary key
#  name       :string           not null
#  created_at :datetime         not null
#  updated_at :datetime         not null
#
require 'rails_helper'

RSpec.describe Group, type: :model do
  describe 'validates' do
    it { should validate_presence_of(:name) }
    it { should have_many(:users).dependent(:nullify) }
    it { should have_many(:habits).dependent(:nullify) }
    it { should have_many(:daily_habits).dependent(:destroy) }
  end

  describe '#missing_daily_habits' do
    let(:user) { create(:user) }
    let(:group) { create(:group) }
    let!(:habits) { create_list(:habit, 3, group:) }
    let(:subject) { group.missing_daily_habits(user:, existing_daily_habits:) }

    describe 'when all daily_habits are missing' do
      let(:existing_daily_habits) { [] }

      it 'returns one daily_habit for each habit' do
        expect(subject.map(&:habit)).to match_array(habits)
      end
    end

    describe 'when no daily_habits are missing' do
      let(:existing_daily_habits) do
        [
          create(:daily_habit, user:, group:, habit: habits[0]),
          create(:daily_habit, user:, group:,  habit: habits[1]),
          create(:daily_habit, user:, group:,  habit: habits[2])
        ]
      end

      it 'returns empty_array' do
        expect(subject).to be_empty
      end
    end

    describe 'when some daily_habits are missing' do
      let(:existing_daily_habits) do
        [
          create(:daily_habit, user:, group:, habit: habits[0])
        ]
      end

      it 'returns the missing daily_habits' do
        expect(subject.map(&:habit)).to match_array([habits[1], habits[2]])
      end
    end
  end
end
