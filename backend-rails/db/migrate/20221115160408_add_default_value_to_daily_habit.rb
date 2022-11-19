class AddDefaultValueToDailyHabit < ActiveRecord::Migration[7.0]
  def change
    change_column :daily_habits, :check, :boolean, default: false
  end
end
