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
class DailyHabit < ApplicationRecord
  validates :date, :check, presence: true

  belongs_to :habit
  belongs_to :user
  belongs_to :group

  scope :by_today, lambda {
    now = Time.zone.now
    where('extract(year from date) = ?', now.year)
      .where('extract(month from date) = ?', now.month)
      .where('extract(day from date) = ?', now.day)
  }
end
