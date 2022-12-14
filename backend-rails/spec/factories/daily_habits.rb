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
FactoryBot.define do
  factory :daily_habit do
    date { Time.zone.now }
    check { true }
    association :group
    association :user
    association :habit
  end
end
