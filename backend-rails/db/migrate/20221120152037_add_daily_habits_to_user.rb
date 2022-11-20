class AddDailyHabitsToUser < ActiveRecord::Migration[7.0]
  def change
    add_column :daily_habits, :user_id, :integer
    add_index  :daily_habits, :user_id
  end
end
