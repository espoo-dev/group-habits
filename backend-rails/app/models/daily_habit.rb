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
class DailyHabit < ApplicationRecord
  validates :date, :check, presence: true

  belongs_to :habit
end
