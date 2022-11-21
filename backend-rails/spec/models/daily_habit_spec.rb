# == Schema Information
#
# Table name: daily_habits
#
#  id         :bigint           not null, primary key
#  date       :date
#  check      :boolean          default(FALSE)
#  habit_id   :bigint           not null
#  created_at :datetime         not null
#  updated_at :datetime         not null
#  user_id    :integer
#  group_id   :integer
#
# Indexes
#
#  index_daily_habits_on_group_id  (group_id)
#  index_daily_habits_on_habit_id  (habit_id)
#  index_daily_habits_on_user_id   (user_id)
#
require 'rails_helper'

RSpec.describe DailyHabit, type: :model do
  context 'relationships' do
    it { should belong_to(:habit) }
    it { should belong_to(:user) }
    it { should belong_to(:group) }
  end

  context 'when attributes are needed' do
    it { should validate_presence_of(:check) }
    it { should validate_presence_of(:date) }
  end

  context 'when create a daily habit' do
    let(:daily_habit) { described_class.new }

    it { expect(daily_habit.check).to be false }
  end
end
