# == Schema Information
#
# Table name: habits
#
#  id         :bigint           not null, primary key
#  name       :string
#  created_at :datetime         not null
#  updated_at :datetime         not null
#
require 'rails_helper'

RSpec.describe Habit, type: :model do
  let(:habit) { build(:habit) }
  let(:incomplete_habit) { build(:habit, name: '') }

  describe '#valid?' do
    context 'when Habit has a name' do
      it 'returns true' do
        expect(habit.valid?).to eq true
      end
    end

    context 'when Habit does not have a name' do
      it 'returns false' do
        expect(incomplete_habit.valid?).to eq false
      end
    end
  end
end
