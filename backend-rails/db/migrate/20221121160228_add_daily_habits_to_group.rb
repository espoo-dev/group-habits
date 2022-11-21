class AddDailyHabitsToGroup < ActiveRecord::Migration[7.0]
  def change
    add_column :daily_habits, :group_id, :integer
    add_index  :daily_habits, :group_id
  end
end
