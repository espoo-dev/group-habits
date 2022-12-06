# == Schema Information
#
# Table name: groups
#
#  id         :bigint           not null, primary key
#  name       :string           not null
#  created_at :datetime         not null
#  updated_at :datetime         not null
#
class Group < ApplicationRecord
  validates :name, presence: true

  has_many :users, dependent: :nullify
  has_many :habits, dependent: :nullify
  has_many :daily_habits, dependent: :destroy

  def missing_daily_habits(user:, existing_daily_habits:)
    existing_habits = existing_daily_habits.map(&:habit)
    missing_habits = habits - existing_habits
    missing_habits.map do |habit|
      DailyHabit.new(user:, habit:, group: self, date: Time.zone.now.beginning_of_day)
    end
  end
end
