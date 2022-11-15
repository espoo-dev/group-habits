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
#
# Indexes
#
#  index_daily_habits_on_habit_id  (habit_id)
#
FactoryBot.define do
  factory :daily_habit do
    date { Time.zone.now }
    check { true }
    habit
  end
end
