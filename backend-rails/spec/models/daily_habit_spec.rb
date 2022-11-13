# == Schema Information
#
# Table name: daily_habits
#
#  id         :bigint           not null, primary key
#  date       :date
#  check      :boolean
#  habit_id   :bigint           not null
#  created_at :datetime         not null
#  updated_at :datetime         not null
#
# Indexes
#
#  index_daily_habits_on_habit_id  (habit_id)
#
require 'rails_helper'

RSpec.describe DailyHabit, type: :model do
  context 'when belongs to habit' do
    it { should belong_to(:habit) }
  end

  context 'when attributes are needed' do
    it { should validate_presence_of(:check) }
    it { should validate_presence_of(:date) }
  end
end
