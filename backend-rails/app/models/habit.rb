# == Schema Information
#
# Table name: habits
#
#  id         :bigint           not null, primary key
#  name       :string
#  created_at :datetime         not null
#  updated_at :datetime         not null
#  icon       :string
#
class Habit < ApplicationRecord
  validates :name, presence: true

  has_many :daily_habits, dependent: :destroy
end
